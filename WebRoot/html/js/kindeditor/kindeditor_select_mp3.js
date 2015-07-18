KindEditor.ready(function(K) {
    var editor = K.editor({
    	formatUploadUrl:false,
        allowFileManager: false,
        uploadJson: "<%=path%>/fileUpload/toServer",
        filePostName:"Filedata",
        extraFileUploadParams:{uploadType:"mp3"},
    });
    $('#bgmusic_obj').live("click",function(e) {
        editor.loadPlugin('image',function() {
            editor.plugin.imageDialog({
                showRemote : false,
                clickFn: function(url, title, width, height, border, align) {
                    $('#bgmusic_name').text("wowoow");
                    editor.hideDialog();
                }
            });
        });
    });
});