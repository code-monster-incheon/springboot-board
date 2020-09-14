var productManager = (function(){

    var register = function (obj, callback) {
        console.log("register.....", obj)

        $.ajax({
            type : 'POST',
            url : '/api/product',
            data : JSON.stringify(obj),
            dataType:'text',
            contentType:'application/json',
            success: callback,
            error:function(request, error)
            {
                alert("상품 등록 에러")
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };

    var update = function(obj, callback)
    {
        console.log("update .....");

        $.ajax({
            type : 'put',
            url:'/api/product/' + obj.productId,
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
            url : '/api/product/' + obj.productId,
            dataType:'text',
            contentType:"application/json",
            success: callback,
        });
    };

    return {
        register:register,
        update: update,
        remove:remove
    }
})();