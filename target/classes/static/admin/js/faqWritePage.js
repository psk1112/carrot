document.addEventListener("DOMContentLoaded", function() {
    var saveButtons = document.getElementById("save-button");

    //에러메시지 표시 함수
    function displayError(fieldId, errorMessage) {
        var errorDiv = document.querySelector(fieldId + " + .errordiv");
        var errorP = document.createElement("p");
            errorP.textContent = errorMessage;
            errorP.style.color = "red";
            errorDiv.appendChild(errorP);
    }

    // 저장 버튼 클릭 시
    saveButtons.addEventListener("click", function(){

        // 이전 에러 메시지 삭제
        document.querySelectorAll(".errordiv").forEach(function (errordiv) {
            errordiv.innerHTML = "";
        });

        var faqKindSeq = document.getElementById("faqKindSeq").value;
        var faqTitle = document.getElementById("faqTitle").value;
        var faqContent = document.getElementById("faqContent").value;

        var data = {
            faqKindSeq: faqKindSeq,
            faqTitle: faqTitle,
            faqContent: faqContent
        };

        var jsonData = JSON.stringify(data)

        // 서버로 POST 요청을 보냄
        fetch('/admin/faq', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                },
            body: jsonData
        })
        .then(response => response.json())
        .then(data => {
            if (data.statusCode === 200){
                Swal.fire({
                    text: '자주 묻는 질문 등록을 완료했습니다.',
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
                    Swal.fire('자주 묻는 질문 등록을 실패했습니다.');
            }
        });
    });
});