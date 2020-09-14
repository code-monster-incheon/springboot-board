var boardManager=(function(){

    var register = function (obj) {
        console.log("board.js [register]", obj)

        $.ajax({
            type : 'POST',
            url : '/api/board',
            data : JSON.stringify(obj),
            dataType:'text',
            contentType:'application/json',
            beforeSend : function(xhr)
            {
                xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
            },
            success: function()
            {
                alert("게시글이 추가 되었습니다.");
                $("textarea#textContent").val("");
                $("input#titleInput").val("");
                window.location.href = "/view/board/list"
            },
            error: function(request, error)
            {
                alert("게시판 등록 에러")
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };

    return {
        register : register
    }
})();