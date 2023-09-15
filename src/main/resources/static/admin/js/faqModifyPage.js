document.addEventListener("DOMContentLoaded", function() {
    var saveButton = document.getElementById("save-button");
    var selectedSeq = document.getElementById("selectedSeq").value;


    //수정하려는 글의 kindSeq를 input에 숨기고 그 값이 selected되게
    var selectElement = document.getElementById("faqKindSeq");
    var optionElements = selectElement.querySelectorAll("option");
    // 각 option 요소를 반복
    optionElements.forEach(function(option) {
        var optionValue = option.getAttribute("value");

        if (optionValue == selectedSeq) {
            option.selected = true;
        }
    });

    //에러메시지 표시 함수
    function displayError(fieldId, errorMessage) {
        var errorDiv = document.querySelector(fieldId + " + .errordiv");
        var errorP = document.createElement("p");
            errorP.textContent = errorMessage;
            errorP.style.color = "red";
            errorDiv.appendChild(errorP);
    }

    //저장버튼 클릭 시
    saveButton.addEventListener("click", function(){

        // 이전 에러 메시지 삭제
        document.querySelectorAll(".errordiv").forEach(function (errordiv) {
            errordiv.innerHTML = "";
        });

        var faqSeq = document.getElementById("faqSeq").value;;
        var faqKindSeq = document.getElementById("faqKindSeq").value;
        var faqTitle = document.getElementById("faqTitle").value;
        var faqContent = document.getElementById("faqContent").value;

        var data = {
                    faqSeq : faqSeq,
                    faqKindSeq: faqKindSeq,
                    faqTitle: faqTitle,
                    faqContent: faqContent
                };

        var jsonData = JSON.stringify(data)

       // 서버로 PUT 요청을 보냄
       fetch('/admin/faq', {
           method: 'PUT',
           headers: {
               'Content-Type': 'application/json'
               },
           body: jsonData
       })
       .then(response => response.json())
       .then(data => {
           if (data.statusCode === 200){
               Swal.fire({
                   text: '자주 묻는 질문 수정을 완료했습니다.',
                   icon: 'success',
                   confirmButtonText: '확인'
               }).then(() => {
                       window.location.href = "/admin/faq";
               })
           }else if(data.statusCode === 401){
               // 각 에러 메시지를 동적으로 표시
               if (data.body.faqKindSeq) {
                   displayError("#faqKindSeq", data.body.faqKindSeq)
               }

               if (data.body.faqTitle) {
                   displayError("#faqTitle", data.body.faqTitle)
               }

               if (data.body.faqContent) {
                   displayError("#faqContent", data.body.faqContent)
               }
           }else {
                   Swal.fire('자주 묻는 질문 수정을 실패했습니다.');
           }
       });

    })

});