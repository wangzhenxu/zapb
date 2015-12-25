/*
 * Inline Form Validation Engine 1.7, jQuery plugin
 * 
 * Copyright(c) 2010, Cedric Dugas
 * http://www.position-relative.net
 *	
 * Form validation engine allowing custom regex rules to be added.
 * Thanks to Francois Duquette and Teddy Limousin 
 * and everyone helping me find bugs on the forum
 * Licenced under the MIT Licence
 * 
 * bluespring 修改1.6.4.1
 *	1.提供选项定制是否关闭提示框箭头 showArray
 * 	2.提供选项定制提示框是否在鼠标放上去的时刻出现还是一直出现 showOnMouseOver
 * 	3.提供选项定制input 效验错误样式
 * 
 * Quentin 修改
 * 	1. 无法全部去掉红色边框的bug   
 * 
 * matychen 修改
 * 	1.把css 中的errorform的padding-bottom改为margin-bottom ，如果不改，在checkbox的时候会遮挡。
 * 	2.当showOnMouseOver为true时。增加处理checkbox时，下面被占用，自己减少了top的高度，如果不减少top的高度，在checkbox的时候会遮挡。
 * 	3.修复了ie6的select遮挡div的bug
 * 	4.增加验证规则存放的属性名称
 * 	5.修改了ajax的发送到客户端的参数名称，返回值不用数组，直接用json对象。
 *		验证规则的名称validateError-->customAjaxRule
 */

(function ($) {

    $.fn.validationEngine = function (settings) {

        if ($.validationEngineLanguage) { // IS THERE A LANGUAGE LOCALISATION ?
            allRules = $.validationEngineLanguage.allRules;
        } else {
            $.validationEngine.debug("没有正确加载外部验证引擎规则,请检查外部文件的引用.");
        }
        settings = jQuery.extend({
            allrules: allRules,
            validationEventTriggers: "focusout",
            inlineValidation: true,
            returnIsValid: false,
            liveEvent: true,
            unbindEngine: true,
            containerOverflow: false,
            containerOverflowDOM: "",
            ajaxSubmit: false,
            scroll: true,
            promptPosition: "topRight",
            // OPENNING BOX POSITION, IMPLEMENTED: topLeft, topRight, bottomLeft, centerRight, bottomRight
            success: false,
            beforeSuccess: function () {},
            failure: function () {},
            //add by bluespring修改 添加是否显示提示箭头，默认显示
            showArrow: true,
            //add by bluespring修改 提示信息是否在鼠标移上去时才显示，默认为是
            showOnMouseOver: true,
            //add by bluepsirng修改 提供效验出错时的field样式定制,必须在showOnMouseOver为true时才有用
            errorClass: 'error-field',
            //add by matychen 验证规则存放的属性名称 ，如：validate="validate[required,custom[noSpecialCaracters],length[0,20]]",默认是class
            validateAttribute:'class'
        }, settings);
        $.validationEngine.settings = settings;
        $.validationEngine.ajaxValidArray = new Array(); // ARRAY FOR AJAX: VALIDATION MEMORY 
        if (settings.inlineValidation == true) { // Validating Inline ?
            if (!settings.returnIsValid) { // NEEDED FOR THE SETTING returnIsValid
                allowReturnIsvalid = false;
                if (settings.liveEvent) { // LIVE event, vast performance improvement over BIND
                    $(this).find("["+$.validationEngine.settings.validateAttribute+"*=validate][type!=checkbox]").live(settings.validationEventTriggers, function (caller) {
                        _inlinEvent(this);
                    });
                    $(this).find("["+$.validationEngine.settings.validateAttribute+"*=validate][type=checkbox]").live("click", function (caller) {
                        _inlinEvent(this);
                    });
                } else {
                    $(this).find("["+$.validationEngine.settings.validateAttribute+"*=validate]").not("[type=checkbox]").bind(settings.validationEventTriggers, function (caller) {
                        _inlinEvent(this);
                    });
                    $(this).find("["+$.validationEngine.settings.validateAttribute+"*=validate][type=checkbox]").bind("click", function (caller) {
                        _inlinEvent(this);
                    });
                }
                firstvalid = false;
            }

            function _inlinEvent(caller) {
                $.validationEngine.settings = settings;
                if ($.validationEngine.intercept == false || !$.validationEngine.intercept) { // STOP INLINE VALIDATION THIS TIME ONLY
                    $.validationEngine.onSubmitValid = false;
                    $.validationEngine.loadValidation(caller);
                } else {
                    $.validationEngine.intercept = false;
                }
            }
        }
        if (settings.returnIsValid) { // Do validation and return true or false, it bypass everything;
            if ($.validationEngine.submitValidation(this, settings)) {
                return false;
            } else {
                return true;
            }
        }
        $(this).bind("submit", function (caller) { // ON FORM SUBMIT, CONTROL AJAX FUNCTION IF SPECIFIED ON DOCUMENT READY
            $.validationEngine.onSubmitValid = true;
            $.validationEngine.settings = settings;
            if ($.validationEngine.submitValidation(this, settings) == false) {
                if ($.validationEngine.submitForm(this, settings) == true) return false;
            } else {
                settings.failure && settings.failure();
                return false;
            }
        });
        //bluespring修改 当设置为鼠标over才显示提示框模式时，去掉点击删除功能
        if (!$.validationEngine.settings.showOnMouseOver) 
        	$(".formError").live("click", function () { // REMOVE BOX ON CLICK
            $(this).fadeOut(150, function () {
                $(this).remove();
            });
        });
    };
    $.validationEngine = {
        defaultSetting: function (caller) { // NOT GENERALLY USED, NEEDED FOR THE API, DO NOT TOUCH
            if ($.validationEngineLanguage) {
                allRules = $.validationEngineLanguage.allRules;
            } else {
                $.validationEngine.debug("没有正确加载外部验证引擎规则,请检查外部文件的引用.");
            }
            settings = {
                allrules: allRules,
                validationEventTriggers: "blur",
                inlineValidation: true,
                containerOverflow: false,
                containerOverflowDOM: "",
                returnIsValid: false,
                scroll: true,
                unbindEngine: true,
                ajaxSubmit: false,
                promptPosition: "topRight",
                // OPENNING BOX POSITION, IMPLEMENTED: topLeft, topRight, bottomLeft, centerRight, bottomRight
                success: false,
                failure: function () {}
            };
            $.validationEngine.settings = settings;
        }, loadValidation: function (caller) { // GET VALIDATIONS TO BE EXECUTED
            if (!$.validationEngine.settings) $.validationEngine.defaultSetting();
            rulesParsing = $(caller).attr($.validationEngine.settings.validateAttribute);
            rulesRegExp = /\[(.*)\]/;
            getRules = rulesRegExp.exec(rulesParsing);
            if (getRules == null) return false;
            str = getRules[1];
            pattern = /\[|,|\]/;
            result = str.split(pattern);
            var validateCalll = $.validationEngine.validateCall(caller, result);
            return validateCalll;
        }, validateCall: function (caller, rules) { // EXECUTE VALIDATION REQUIRED BY THE USER FOR THIS FIELD
            var promptText = "";
            if (!$(caller).attr("id")) 
            	$.validationEngine.debug("这个地方没有ID这个属性( name & class displayed): " + $(caller).attr("name") + " " + $(caller).attr("class"));
            caller = caller;
            ajaxValidate = false;
            var callerName = $(caller).attr("name");
            $.validationEngine.isError = false;
            //兰文栋修改   将默认打开的提示箭头设置为选项值
            $.validationEngine.showTriangle = $.validationEngine.settings.showArrow;
            callerType = $(caller).attr("type");

            for (i = 0; i < rules.length; i++) {
                switch (rules[i]) {
                case "optional":
                    if (!$(caller).val()) {
                        $.validationEngine.closePrompt(caller);
                        return $.validationEngine.isError;
                    }
                    break;
                case "required":
                    _required(caller, rules);
                    break;
                case "custom":
                    _customRegex(caller, rules, i);
                    break;
                case "exemptString":
                    _exemptString(caller, rules, i);
                    break;
                case "ajax":
                    if (!$.validationEngine.onSubmitValid) _ajax(caller, rules, i);
                    break;
                case "length":
                    _length(caller, rules, i);
                    break;
                    //bluespring添加 效验取值区间
                case "region":
                    _region(caller, rules, i);
                    break;
                case "maxCheckbox":
                    _maxCheckbox(caller, rules, i);
                    groupname = $(caller).attr("name");
                    caller = $("input[name='" + groupname + "']");
                    break;
                case "minCheckbox":
                    _minCheckbox(caller, rules, i);
                    groupname = $(caller).attr("name");
                    caller = $("input[name='" + groupname + "']");
                    break;
                case "confirm":
                    _confirm(caller, rules, i);
                    break;
                case "funcCall":
                	_funcCall(caller, rules, i);
                	break;
                default:
                	;
                };
            };
            radioHack();
            if ($.validationEngine.isError == true) {
                var linkTofieldText = "." + $.validationEngine.linkTofield(caller);
                if (linkTofieldText != ".") {
                    if (!$(linkTofieldText)[0]) {
                        $.validationEngine.buildPrompt(caller, promptText, "error");
                    } else {
                        $.validationEngine.updatePromptText(caller, promptText);
                    }
                } else {
                    $.validationEngine.updatePromptText(caller, promptText);
                }
            } else {
                $.validationEngine.closePrompt(caller);
            } /* UNFORTUNATE RADIO AND CHECKBOX GROUP HACKS */
            /* As my validation is looping input with id's we need a hack for my validation to understand to group these inputs */

            function radioHack() {
                if ($("input[name='" + callerName + "']").size() > 1 && (callerType == "radio" || callerType == "checkbox")) { // Hack for radio/checkbox group button, the validation go the first radio/checkbox of the group
                    caller = $("input[name='" + callerName + "'][type!=hidden]:first");
                    $.validationEngine.showTriangle = false;
                }
            } /* VALIDATION FUNCTIONS */

            function _required(caller, rules) { // VALIDATE BLANK FIELD
                callerType = $(caller).attr("type");
                if (callerType == "text" || callerType == "password" || callerType == "textarea") {

                    if (!$(caller).val()) {
                        $.validationEngine.isError = true;
                        promptText += $.validationEngine.settings.allrules[rules[i]].alertText + "<br />";
                    }
                }
                if (callerType == "radio" || callerType == "checkbox") {
                    callerName = $(caller).attr("name");
                    
                    if ($("input[name='" + callerName + "']:checked").size() == 0) {
                        $.validationEngine.isError = true;
                        $.validationEngine.showTriangle = false;
                        $.validationEngine.addTop = true;//如果是checkbox 把高度增高 add by matychen
                        if ($("input[name='" + callerName + "']").size() == 1) {
                            promptText += $.validationEngine.settings.allrules[rules[i]].alertTextCheckboxe + "<br />";
                        } else {
                            promptText += $.validationEngine.settings.allrules[rules[i]].alertTextCheckboxMultiple + "<br />";
                        }
                    }
                }
                if (callerType == "select-one") { // added by paul@kinetek.net for select boxes, Thank you		
                    if (!$(caller).val()) {
                        $.validationEngine.isError = true;
                        promptText += $.validationEngine.settings.allrules[rules[i]].alertText + "<br />";
                    }
                }
                if (callerType == "select-multiple") { // added by paul@kinetek.net for select boxes, Thank you	
                    if (!$(caller).find("option:selected").val()) {
                        $.validationEngine.isError = true;
                        promptText += $.validationEngine.settings.allrules[rules[i]].alertText + "<br />";
                    }
                }
            }

            function _customRegex(caller, rules, position) { // VALIDATE REGEX RULES
            	customRule = rules[position+1];
    			pattern = eval($.validationEngine.settings.allrules[customRule].regex);
    			
    			if(!pattern.test($(caller).attr('value'))){
    				$.validationEngine.isError = true;
    				promptText += $.validationEngine.settings.allrules[customRule].alertText+"<br />";
    			}
            }

            function _exemptString(caller, rules, position) { // VALIDATE REGEX RULES
                customString = rules[position + 1];
                if (customString == $(caller).attr('value')) {
                    $.validationEngine.isError = true;
                    promptText += $.validationEngine.settings.allrules['required'].alertText + "<br />";
                }
            }

            function _funcCall(caller, rules, position) { // VALIDATE CUSTOM FUNCTIONS OUTSIDE OF THE ENGINE SCOPE
                customRule = rules[position + 1];
                funce = $.validationEngine.settings.allrules[customRule].nname;

                var fn = window[funce];
                if (typeof(fn) === 'function') {
                    var fn_result = fn();
                    if (!fn_result) {
                        $.validationEngine.isError = true;
                    }

                    promptText += $.validationEngine.settings.allrules[customRule].alertText + "<br />";
                }
            }

            function _ajax(caller, rules, position) { // VALIDATE AJAX RULES
                customAjaxRule = rules[position + 1];
                postfile = $.validationEngine.settings.allrules[customAjaxRule].file;
                fieldValue = $(caller).val();
                ajaxCaller = caller;
                fieldId = $(caller).attr("id");
                ajaxValidate = true;
                ajaxisError = $.validationEngine.isError;

                if ($.validationEngine.settings.allrules[customAjaxRule].extraData) {
                    extraData = $.validationEngine.settings.allrules[customAjaxRule].extraData;
                } else {
                    extraData = "";
                } /* AJAX VALIDATION HAS ITS OWN UPDATE AND BUILD UNLIKE OTHER RULES */
                if (!ajaxisError) {
                    $.ajax({
                        type: "POST",
                        url: postfile,
                        //async: true,//要验证的值						验证值的名称				验证规则的名称validateError-->customAjaxRule
                        data: "validateValue=" + fieldValue + "&validateId=" + fieldId + "&customAjaxRule=" + customAjaxRule + "&extraData=" + extraData,
                        beforeSend: function () { // BUILD A LOADING PROMPT IF LOAD TEXT EXIST		   			
                            if ($.validationEngine.settings.allrules[customAjaxRule].alertTextLoad) {
                                if (!$("div." + fieldId + "formError")[0]) {
                                    return $.validationEngine.buildPrompt(ajaxCaller, $.validationEngine.settings.allrules[customAjaxRule].alertTextLoad, "load");
                                } else {
                                    $.validationEngine.updatePromptText(ajaxCaller, $.validationEngine.settings.allrules[customAjaxRule].alertTextLoad, "load");
                                }
                            }
                        }, error: function (data, transport) {
                            $.validationEngine.debug("Ajax 错误: " + data.status + " " + transport);
                        }, success: function (data) { // GET SUCCESS DATA RETURN JSON
                        	//	jquery 已经转为json了，所以不要这句话，不然会报：missing ] after element list的错误
							//  data = eval("(" + data + ")"); // GET JSON DATA FROM PHP AND PARSE IT
                            // add by matychen 不用数组，直接用json对象。例如：{"validateId":"user","ajaxisError":false,"customAjaxRule":"ajaxUser"}
                            ajaxisError = data.ajaxisError; //true or false 
                            customAjaxRule = data.customAjaxRule; //验证规则的名称
                            ajaxCaller = $("#" + data.validateId)[0]; //验证值的id
                            //
                            fieldId = ajaxCaller;
                            ajaxErrorLength = $.validationEngine.ajaxValidArray.length;
                            existInarray = false;
							//对象就不能是"false"了
                            if (ajaxisError == false) { // DATA FALSE UPDATE PROMPT WITH ERROR;
                                _checkInArray(false); // Check if ajax validation alreay used on this field
                                if (!existInarray) { // Add ajax error to stop submit		 		
                                    $.validationEngine.ajaxValidArray[ajaxErrorLength] = new Array(2);
                                    $.validationEngine.ajaxValidArray[ajaxErrorLength][0] = fieldId;
                                    $.validationEngine.ajaxValidArray[ajaxErrorLength][1] = false;
                                    existInarray = false;
                                }
                                $.validationEngine.ajaxValid = false;
                                promptText += $.validationEngine.settings.allrules[customAjaxRule].alertText + "<br />";
                                $.validationEngine.updatePromptText(ajaxCaller, promptText, "", true);
                            } else {
                                _checkInArray(true);
                                $.validationEngine.ajaxValid = true;
                                if (!customAjaxRule) {
                                    $.validationEngine.debug("customAjaxRule没有返回值, 请检查. ");
                                }
                                if ($.validationEngine.settings.allrules[customAjaxRule].alertTextOk) { // NO OK TEXT MEAN CLOSE PROMPT	 			
                                    $.validationEngine.updatePromptText(ajaxCaller, $.validationEngine.settings.allrules[customAjaxRule].alertTextOk, "pass", true);
                                } else {
                                    ajaxValidate = false;
                                    $.validationEngine.closePrompt(ajaxCaller);
                                }
                            }

                            function _checkInArray(validate) {
                                for (x = 0; x < ajaxErrorLength; x++) {
                                    if ($.validationEngine.ajaxValidArray[x][0] == fieldId) {
                                        $.validationEngine.ajaxValidArray[x][1] = validate;
                                        existInarray = true;
                                    }
                                }
                            }
                        }
                    });
                }
            }

            function _confirm(caller, rules, position) { // VALIDATE FIELD MATCH
                confirmField = rules[position + 1];

                if ($(caller).attr('value') != $("#" + confirmField).attr('value')) {
                    $.validationEngine.isError = true;
                    promptText += $.validationEngine.settings.allrules["confirm"].alertText + "<br />";
                }
            }

            function _length(caller, rules, position) { // VALIDATE LENGTH
                startLength = eval(rules[position + 1]);
                endLength = eval(rules[position + 2]);
                feildLength = $(caller).attr('value').length;

                if (feildLength < startLength || feildLength > endLength) {
                    $.validationEngine.isError = true;
                    promptText += $.validationEngine.settings.allrules["length"].alertText + startLength + $.validationEngine.settings.allrules["length"].alertText2 + endLength + $.validationEngine.settings.allrules["length"].alertText3 + "<br />";
                }
            }
            //bluespring添加 效验取值区间

            function _region(caller, rules, position) {
                var min = eval(rules[position + 1]);
                var max = eval(rules[position + 2]);
                var value;
                try {
                    value = parseFloat($(caller).attr('value'));
                } catch (e) {
                    promptText += $.validationEngine.settings.allrules["region"].alertText + min + $.validationEngine.settings.allrules["region"].alertText2 + max + $.validationEngine.settings.allrules["region"].alertText3 + "<br />";
                    $.validationEngine.isError = true;
                    return;
                }
                if (value < min || value > max) {
                    promptText += $.validationEngine.settings.allrules["region"].alertText + min + $.validationEngine.settings.allrules["region"].alertText2 + max + $.validationEngine.settings.allrules["region"].alertText3 + "<br />";
                    $.validationEngine.isError = true;
                }
            }

            function _maxCheckbox(caller, rules, position) { // VALIDATE CHECKBOX NUMBER
                nbCheck = eval(rules[position + 1]);
                groupname = $(caller).attr("name");
                groupSize = $("input[name='" + groupname + "']:checked").size();
                if (groupSize > nbCheck) {
                    $.validationEngine.showTriangle = false;
                    $.validationEngine.isError = true;
                    $.validationEngine.addTop = true;//如果是checkbox 把高度增高 add by matychen
                    promptText += $.validationEngine.settings.allrules["maxCheckbox"].alertText + "<br />";
                }
            }

            function _minCheckbox(caller, rules, position) { // VALIDATE CHECKBOX NUMBER
                nbCheck = eval(rules[position + 1]);
                groupname = $(caller).attr("name");
                groupSize = $("input[name='" + groupname + "']:checked").size();
                if (groupSize < nbCheck) {
                    $.validationEngine.isError = true;
                    $.validationEngine.showTriangle = false;
                    $.validationEngine.addTop = true;//如果是checkbox 把高度增高 add by matychen
                    promptText += $.validationEngine.settings.allrules["minCheckbox"].alertText + " " + nbCheck + " " + $.validationEngine.settings.allrules["minCheckbox"].alertText2 + "<br />";
                }
            }
            return ($.validationEngine.isError) ? $.validationEngine.isError : false;
        }, submitForm: function (caller) {
            if ($.validationEngine.settings.ajaxSubmit) {
                if ($.validationEngine.settings.ajaxSubmitExtraData) {
                    extraData = $.validationEngine.settings.ajaxSubmitExtraData;
                } else {
                    extraData = "";
                }
                $.ajax({
                    type: "POST",
                    url: $.validationEngine.settings.ajaxSubmitFile,
                    async: true,
                    data: $(caller).serialize() + "&" + extraData,
                    error: function (data, transport) {
                        $.validationEngine.debug("Ajax 错误: " + data.status + " " + transport);
                    }, success: function (data) {
                        if (data == "true") { // EVERYTING IS FINE, SHOW SUCCESS MESSAGE
                            $(caller).css("opacity", 1);
                            $(caller).animate({
                                opacity: 0,
                                height: 0
                            }, function () {
                                $(caller).css("display", "none");
                                $(caller).before("<div class='ajaxSubmit'>" + $.validationEngine.settings.ajaxSubmitMessage + "</div>");
                                $.validationEngine.closePrompt(".formError", true);
                                $(".ajaxSubmit").show("slow");
                                if ($.validationEngine.settings.success) { // AJAX SUCCESS, STOP THE LOCATION UPDATE
                                    $.validationEngine.settings.success && $.validationEngine.settings.success();
                                    return false;
                                }
                            });
                        } else { // HOUSTON WE GOT A PROBLEM (SOMETING IS NOT VALIDATING)
                            data = eval("(" + data + ")");
                            if (!data.jsonValidateReturn) {
                                $.validationEngine.debug("没有正确的执行success这个函数并且jsonValidateReturn返回空值.");
                            }
                            errorNumber = data.jsonValidateReturn.length;
                            for (index = 0; index < errorNumber; index++) {
                                fieldId = data.jsonValidateReturn[index][0];
                                promptError = data.jsonValidateReturn[index][1];
                                type = data.jsonValidateReturn[index][2];
                                $.validationEngine.buildPrompt(fieldId, promptError, type);
                            }
                        }
                    }
                });
                return true;
            }
            // LOOK FOR BEFORE SUCCESS METHOD		
            if (!$.validationEngine.settings.beforeSuccess()) {
                if ($.validationEngine.settings.success) { // AJAX SUCCESS, STOP THE LOCATION UPDATE
                    if ($.validationEngine.settings.unbindEngine) {
                        $(caller).unbind("submit");
                    }
                    $.validationEngine.settings.success && $.validationEngine.settings.success();
                    return true;
                }
            } else {
                return true;
            }
            return false;
        },
        //bluespring修改 当鼠标over 及out时的提示显示方法
        showTip: function (event) {
            event.data.stop();
            event.data.fadeTo('normal', 1,function () {
                $(this).show();
            });
        }, hideTip: function (event) {
            event.data.stop();
            event.data.fadeTo('normal', 0, function () {
                $(this).hide();
            });
        }, buildPrompt: function (caller, promptText, type, ajaxed) { // ERROR PROMPT CREATION AND DISPLAY WHEN AN ERROR OCCUR
            if (!$.validationEngine.settings) {
                $.validationEngine.defaultSetting();
            }
            deleteItself = "." + $(caller).attr("id") + "formError";

            if ($(deleteItself)[0]) {
                $(deleteItself).stop();
                $(deleteItself).remove();
            }
            var divFormError = document.createElement('div');
            var formErrorContent = document.createElement('div');
            linkTofield = $.validationEngine.linkTofield(caller);
            $(divFormError).addClass("formError");

            if (type == "pass") $(divFormError).addClass("greenPopup");
            if (type == "load") $(divFormError).addClass("blackPopup");
            if (ajaxed) $(divFormError).addClass("ajaxed");

            $(divFormError).addClass(linkTofield);
            $(formErrorContent).addClass("formErrorContent");

            if ($.validationEngine.settings.containerOverflow) { // Is the form contained in an overflown container?
                $(caller).before(divFormError);
            } else {
                $("body").append(divFormError);
            }

            $(divFormError).append(formErrorContent);
            if ($.validationEngine.showTriangle != false) { // NO TRIANGLE ON MAX CHECKBOX AND RADIO
                var arrow = document.createElement('div');
                $(arrow).addClass("formErrorArrow");
                $(divFormError).append(arrow);
                if ($.validationEngine.settings.promptPosition == "bottomLeft" || $.validationEngine.settings.promptPosition == "bottomRight") {
                    $(arrow).addClass("formErrorArrowBottom");
                    $(arrow).html('<div class="line1"><!-- --></div><div class="line2"><!-- --></div><div class="line3"><!-- --></div><div class="line4"><!-- --></div><div class="line5"><!-- --></div><div class="line6"><!-- --></div><div class="line7"><!-- --></div><div class="line8"><!-- --></div><div class="line9"><!-- --></div><div class="line10"><!-- --></div>');
                }
                if ($.validationEngine.settings.promptPosition == "topLeft" || $.validationEngine.settings.promptPosition == "topRight") {
                    $(divFormError).append(arrow);
                    $(arrow).html('<div class="line10"><!-- --></div><div class="line9"><!-- --></div><div class="line8"><!-- --></div><div class="line7"><!-- --></div><div class="line6"><!-- --></div><div class="line5"><!-- --></div><div class="line4"><!-- --></div><div class="line3"><!-- --></div><div class="line2"><!-- --></div><div class="line1"><!-- --></div>');
                }
            }
            $(formErrorContent).html(promptText);

            var calculatedPosition = $.validationEngine.calculatePosition(caller, promptText, type, ajaxed, divFormError);

            calculatedPosition.callerTopPosition += "px";
            calculatedPosition.callerleftPosition += "px";
            calculatedPosition.marginTopSize += "px";
            
            $(divFormError).css({
                "top": calculatedPosition.callerTopPosition,
                "left": calculatedPosition.callerleftPosition,
                "marginTop": calculatedPosition.marginTopSize,
                "opacity": 0
            });
            //add by  matychen 
            if ($.browser.msie && /6.0/.test(navigator.userAgent)) {
                $(divFormError).append('<iframe style="display:none" class="iframe" frameborder="0" scr="javascript:false;"></iframe>');
                //在ie6下面，有select，这个div会遮挡select，这个就先隐藏,如果不隐藏，那就不用隐藏
                if ($.validationEngine.settings.showOnMouseOver) {
                  $(divFormError).hide();
                }
            }
            // add by  matychen
            //bluespring修改  鼠标over时才出现提示框,添加效验错误class
            if ($.validationEngine.settings.showOnMouseOver) {
                if ($(caller).is(':checkbox,:radio')) {
                    $(divFormError).get(0).validateField = $(caller).parent();
                } else {
                    $(divFormError).get(0).validateField = $(caller);
                }
                $(divFormError).get(0).validateField.addClass($.validationEngine.settings.errorClass);
                $(divFormError).get(0).validateField.bind('mouseover', $(divFormError), $.validationEngine.showTip).bind('mouseout', $(divFormError), $.validationEngine.hideTip);
            } else {
                return $(divFormError).animate({
                    "opacity": 0.87
                }, function () {
                    return true;
                });
            }
        }, updatePromptText: function (caller, promptText, type, ajaxed) { // UPDATE TEXT ERROR IF AN ERROR IS ALREADY DISPLAYED
            linkTofield = $.validationEngine.linkTofield(caller);
            var updateThisPrompt = "." + linkTofield;

            if (type == "pass") {
                $(updateThisPrompt).addClass("greenPopup");
                //bluespring修改 
                if ($.validationEngine.settings.showOnMouseOver) {
                    if ($(updateThisPrompt).get(0)) 
                    $(updateThisPrompt).get(0).validateField.removeClass($.validationEngine.settings.errorClass)
                    .unbind('mouseover', $.validationEngine.showTip)
                    .unbind('mouseout', $.validationEngine.hideTip);
                    $(updateThisPrompt).remove();
                } else {
                    $(updateThisPrompt).fadeTo("fast", 0, function () {
                        $(updateThisPrompt).remove();
                    });
                }
            } else {
                $(updateThisPrompt).removeClass("greenPopup");
            };
            if (type == "load") {
                $(updateThisPrompt).addClass("blackPopup");
            } else {
                $(updateThisPrompt).removeClass("blackPopup");
            };
            if (ajaxed) {
                $(updateThisPrompt).addClass("ajaxed");
            } else {
                $(updateThisPrompt).removeClass("ajaxed");
            };

            $(updateThisPrompt).find(".formErrorContent").html(promptText);

            var calculatedPosition = $.validationEngine.calculatePosition(caller, promptText, type, ajaxed, updateThisPrompt);

            calculatedPosition.callerTopPosition += "px";
            calculatedPosition.callerleftPosition += "px";
            calculatedPosition.marginTopSize += "px";
            $(updateThisPrompt).animate({
                "top": calculatedPosition.callerTopPosition,
                "marginTop": calculatedPosition.marginTopSize
            });
        }, calculatePosition: function (caller, promptText, type, ajaxed, divFormError) {

            if ($.validationEngine.settings.containerOverflow) { // Is the form contained in an overflown container?
                callerTopPosition = 0;
                callerleftPosition = 0;
                callerWidth = $(caller).width();
                inputHeight = $(divFormError).height(); // compasation for the triangle
                var marginTopSize = "-" + inputHeight;
            } else {
                callerTopPosition = $(caller).offset().top;
                callerleftPosition = $(caller).offset().left;
                callerWidth = $(caller).width();
                inputHeight = $(divFormError).height();
                var marginTopSize = 0;
            }

            /* POSITIONNING */
            if ($.validationEngine.settings.promptPosition == "topRight") {
                if ($.validationEngine.settings.containerOverflow) { // Is the form contained in an overflown container?
                    callerleftPosition += callerWidth - 30;
                } else {
                    callerleftPosition += callerWidth - 30;
                    if( $.validationEngine.addTop){
                    	// 减少top的值，使整个div上移，不然会挡住下面的checkbox add matychen
                    	callerTopPosition += -inputHeight-10;
                    	$.validationEngine.addTop=false;
                    }
                    else{
                    	 callerTopPosition += -inputHeight;
                    }
                }
            }
            if ($.validationEngine.settings.promptPosition == "topLeft") {
                callerTopPosition += -inputHeight - 10;
            }

            if ($.validationEngine.settings.promptPosition == "centerRight") {
                callerleftPosition += callerWidth + 13;
            }

            if ($.validationEngine.settings.promptPosition == "bottomLeft") {
                callerHeight = $(caller).height();
                callerTopPosition = callerTopPosition + callerHeight + 15;
            }
            if ($.validationEngine.settings.promptPosition == "bottomRight") {
                callerHeight = $(caller).height();
                callerleftPosition += callerWidth - 30;
                callerTopPosition += callerHeight + 5;
            }
            return {
                "callerTopPosition": callerTopPosition,
                "callerleftPosition": callerleftPosition,
                "marginTopSize": marginTopSize
            };
        }, linkTofield: function (caller) {
        	 var linkTofield = $(caller).attr("id") + "formError";
             linkTofield = linkTofield.replace(/\./g,"\\.");  
             linkTofield = linkTofield.replace(/\//g,"\\/");  
             linkTofield = linkTofield.replace(/\$/g,"\\$");  
             linkTofield = linkTofield.replace(/\[/g,"\\[");  
             linkTofield = linkTofield.replace(/\]/g,"\\]");
            return linkTofield;
        }, closePrompt: function (caller, outside) { // CLOSE PROMPT WHEN ERROR CORRECTED
            if (!$.validationEngine.settings) {
                $.validationEngine.defaultSetting();
            }
            if (outside) {
                //bluespring修改 
                if ($.validationEngine.settings.showOnMouseOver) {
                    // Quentin 修改，无法全部去掉红色边框的bug   
                    for (var i = 0; i < $(caller).size(); i++) {
                        $(caller).get(i).validateField.removeClass($.validationEngine.settings.errorClass)
                        		.unbind('mouseover',$.validationEngine.showTip)
                        		.unbind('mouseout', $.validationEngine.hideTip);
                    }
                    $(caller).remove();
                } else {
                    $(caller).fadeTo("fast", 0, function () {
                        $(caller).remove();
                    });
                }
                return false;
            }
            if (typeof(ajaxValidate) == 'undefined') {
                ajaxValidate = false;
            };
            if (!ajaxValidate) {
                linkTofield = $.validationEngine.linkTofield(caller);
                closingPrompt = "." + linkTofield;
                //bluespring修改 
                if ($.validationEngine.settings.showOnMouseOver) {
                    if ($(closingPrompt).get(0)) $(closingPrompt).get(0).validateField.removeClass($.validationEngine.settings.errorClass).unbind('mouseover', $.validationEngine.showTip).unbind('mouseout', $.validationEngine.hideTip);
                    $(closingPrompt).remove();
                } else {
                    $(closingPrompt).fadeTo("fast", 0, function () {
                        $(closingPrompt).remove();
                    });
                }
            }
        }, debug: function (error) {
            if (!$("#debugMode")[0]) {
                $("body").append("<div id='debugMode'><div class='debugError'><strong>This is a debug mode, you got a problem with your form, it will try to help you, refresh when you think you nailed down the problem</strong></div></div>");
            }
            $(".debugError").append("<div class='debugerror'>" + error + "</div>");
        }, submitValidation: function (caller) { // FORM SUBMIT VALIDATION LOOPING INLINE VALIDATION
            var stopForm = false;
            $.validationEngine.ajaxValid = true;
            var toValidateSize = $(caller).find("["+$.validationEngine.settings.validateAttribute+"*=validate]").size();

            $(caller).find("["+$.validationEngine.settings.validateAttribute+"*=validate]").each(function () {
                linkTofield = $.validationEngine.linkTofield(this);

                if (!$("." + linkTofield).hasClass("ajaxed")) { // DO NOT UPDATE ALREADY AJAXED FIELDS (only happen if no normal errors, don't worry)
                    var validationPass = $.validationEngine.loadValidation(this);
                    return (validationPass) ? stopForm = true : "";
                };
            });
            ajaxErrorLength = $.validationEngine.ajaxValidArray.length; // LOOK IF SOME AJAX IS NOT VALIDATE
            for (x = 0; x < ajaxErrorLength; x++) {
                if ($.validationEngine.ajaxValidArray[x][1] == false) $.validationEngine.ajaxValid = false;
            }
            if (stopForm || !$.validationEngine.ajaxValid) { // GET IF THERE IS AN ERROR OR NOT FROM THIS VALIDATION FUNCTIONS
                if ($.validationEngine.settings.scroll) {
                    if (!$.validationEngine.settings.containerOverflow) {
                        var destination = $(".formError:not('.greenPopup'):first").offset().top;
                        $(".formError:not('.greenPopup')").each(function () {
                            testDestination = $(this).offset().top;
                            if (destination > testDestination) destination = $(this).offset().top;
                        });
                        $("html:not(:animated),body:not(:animated)").animate({
                            scrollTop: destination
                        }, 1100);
                    } else {
                        var destination = $(".formError:not('.greenPopup'):first").offset().top;
                        var scrollContainerScroll = $($.validationEngine.settings.containerOverflowDOM).scrollTop();
                        var scrollContainerPos = -parseInt($($.validationEngine.settings.containerOverflowDOM).offset().top);
                        var destination = scrollContainerScroll + destination + scrollContainerPos - 5;
                        var scrollContainer = $.validationEngine.settings.containerOverflowDOM + ":not(:animated)";

                        $(scrollContainer).animate({
                            scrollTop: destination
                        }, 1100);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };
})(jQuery);