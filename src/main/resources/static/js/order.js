var orderManager = (function(){

    var register = function(obj, callback) {
        console.log("order.....", obj);

        var productList = [];

        var objs = {"prodId" : obj.id, "cnt" : obj.cnt};
        productList.push(objs);

        $.ajax({
            type : 'POST',
            url : '/api/order',
            data : JSON.stringify(productList),
            dataType:'text',
            contentType:'application/json',
            success: callback,

            error:function(request, error){
                alert("상품 주문 에러");
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };
    return {
        register:register
    }
})();