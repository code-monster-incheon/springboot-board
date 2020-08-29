var replyManager = (function(){

    var getAll = function(obj, callback)
    {
        $.getJSON('/api/replies/' + obj, callback);
    };



    var add = function(obj, callback)
    {
        console.log("add ...");

        $.ajax({
            type : 'post',
            url : '/api/replies/' + obj.id,
            data : JSON.stringify(obj),
            dataType:'json',
            contentType:'application/json',
            success:callback
        })
    };

    var update = function(obj, callback)
    {

    };

    var remove = function(obj, callback)
    {

    };

    return {
        getAll : getAll,
        add  : add,
        update: update,
        remove:remove
    }
})();