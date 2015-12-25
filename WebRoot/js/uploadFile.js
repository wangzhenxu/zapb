 $("#dropz").dropzone({
        url: "/file/upload2.action",
        acceptedFiles: ".png,.jgp,.gif",
        uploadMultiple:true,
        maxFilesize:2,
        maxFiles:10,
        addRemoveLinks:true,
        dictRemoveLinks: "x",
        dictCancelUpload: "x",
        headers:{"type":1},
        init: function() {
            this.on("success", function(file,data) {
            	
                console.log("File " + file.name + "uploaded");
            });
            this.on("removedfile", function(file) {
                console.log("File " + file.name + "removed");
            });
        }
    });