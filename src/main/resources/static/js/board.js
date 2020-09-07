var boardManager=(function(){

    var register = function (obj, callback) {
        console.log("register.....", obj)

        $.ajax({
            type : 'POST',
            url : "/api/board",
            data : JSON.stringify(obj),
            dataType:'text',
            contentType:'application/json',
            success: callback
        });
    };
    return {
     register:register
    }
})()