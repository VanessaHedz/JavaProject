$(document).ready(function(){

    var btn = $("#btnA");


    btn.on('click', function(){
        console.log("AAAAAAAAAAAAAAAAAAAAAAAAAA");

        $.ajax({
            url:"e1.java",
            method:"post",
            dataType:'json',
            success: function(){
                console.log("siuuuuuuuuuuuuu");
            },
            error: function(xhr, status, error){

            }
        });

    })

});