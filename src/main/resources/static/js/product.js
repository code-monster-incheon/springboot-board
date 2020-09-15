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
            error:function(request, error){
                alert("상품 등록 에러")
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };
    return {
        register:register
    }
})();