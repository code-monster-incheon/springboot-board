var boardManager=(function(){

    var register = function (obj, callback) {
        console.log("register.....", obj)

        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/api/board',
            data : JSON.stringify(obj),
            dataType:'json',
            contentType:'application/json',
            success: callback
        });
    };
    return {
     register:register
    }
})()