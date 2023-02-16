// (1) 회원정보 수정
function update(userId,event) {

    event.preventDefault(); // 폼태그 액션을 막기!!
    let d = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url:'/user/'+userId,
        data: d,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json",

    }).done(res=>{ //HttpStatus 상태코드 200번대

        location.href="/user/"+userId;
    }).fail(error=>{//HttpStatus 상태코드 200번대 아닐때
        if(error.data==null){
            alert(error.responseJSON.message);
        }else{
            alert(error.responseJSON.data.name);
        }

        console.log("실패");
    });

}