var replyManager = (function(){

    // 게시판에 달린 모든 댓글을 가져오는 작업
    var getAll = function(obj, callback)
    {
        $.getJSON('/api/replies/' + obj, callback);
    };

    // 댓글 추가하는 작업
    var add = function(obj, callback)
    {
        console.log("add ...");

        $.ajax({
            type : 'post',
            url : '/api/replies/' + obj.id,
            data : JSON.stringify(obj),
            dataType:'json',
            contentType:"application/json",
            success:callback
        });
    };

    var update = function(obj, callback)
    {
        console.log("update .....");

        $.ajax({
            type : 'put',
            url:'/api/replies/' + obj.replyId,
            dataType:'json',
            data:JSON.stringify(obj),
            contentType:"application/json",
            success:callback
        });
    };

    var remove = function(obj, callback)
    {
        console.log("remove .........");

        $.ajax({
            type : 'delete',
            url : '/api/replies/' + obj.replyId,
            dataType:'json',
            contentType:"application/json",
            success:callback
        });
    };

    return {
        getAll : getAll,
        add  : add,
        update: update,
        remove:remove
    }
})();