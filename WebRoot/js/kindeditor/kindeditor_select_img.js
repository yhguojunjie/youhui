KindEditor.ready(function(K) {
    var editor = K.editor({
    	formatUploadUrl:false,
        allowFileManager: false,
        uploadJson: "<%=path%>/fileUpload/toServer",
        filePostName:"Filedata",
        extraFileUploadParams:{uploadType:"image"},
    });
    $('.fileuploadclass').live("click",function(e) {
        editor.loadPlugin('image',function() {
            editor.plugin.imageDialog({
                showRemote : false,
                clickFn: function(url, title, width, height, border, align) {
                    $(e.target).parent().prev().find('.imghead').attr("src","${resUrl}"+url);
                    editor.hideDialog();
                }
            });
        });
    });
});