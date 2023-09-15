// 더 보기 링크 클릭 시 모달 열기
function openModal() {
    var imageModal = new bootstrap.Modal(document.getElementById("imageModal"));
        var carousel = document.querySelector("#imageCarousel");

        // 케러셀 초기화 (첫 번째 이미지부터 시작하도록)
        var firstSlide = carousel.querySelector(".carousel-item");
        firstSlide.classList.add("active");

        // 다음 버튼을 누를 때 슬라이드 변경
            var nextButton = document.querySelector("#imageCarousel .carousel-control-next");
            nextButton.addEventListener("click", function() {
                var activeSlide = carousel.querySelector(".carousel-item.active");
                var nextSlide = activeSlide.nextElementSibling;

                if (!nextSlide) {
                    nextSlide = carousel.querySelector(".carousel-item:first-child");
                }

                activeSlide.classList.remove("active");
                nextSlide.classList.add("active");
            });

            // 이전 버튼을 누를 때 슬라이드 변경
            var prevButton = document.querySelector("#imageCarousel .carousel-control-prev");
            prevButton.addEventListener("click", function() {
                var activeSlide = carousel.querySelector(".carousel-item.active");
                var prevSlide = activeSlide.previousElementSibling;

                if (!prevSlide) {
                    prevSlide = carousel.querySelector(".carousel-item:last-child");
                }

                activeSlide.classList.remove("active");
                prevSlide.classList.add("active");
            });

        imageModal.show();
}


document.addEventListener("DOMContentLoaded", function() {

    var modifyButton = document.getElementById("modify-button");
    var saveButton = document.getElementById("save-button");
    var deleteButton = document.getElementById("delete-button");

    // 수정 버튼 클릭 시
    modifyButton.addEventListener("click", function(){
        var answerInput = document.getElementById("questionAnswerContent");
        answerInput.removeAttribute("disabled");
        saveButton.hidden = false;
        modifyButton.hidden = true;
        deleteButton.hidden = true;

    });

    // 에러 메시지 표시 함수
    function displayError(fieldId, errorMessage) {
        var errorDiv = document.querySelector(fieldId + " + .errordiv");
        var errorP = document.createElement("p");
            errorP.textContent = errorMessage;
            errorP.style.color = "red";
            errorDiv.appendChild(errorP);
    }

    // 저장 버튼 클릭 시
    saveButton.addEventListener("click", function(){

        // 이전 에러 메시지 삭제
        document.querySelectorAll(".errordiv").forEach(function (errordiv) {
            errordiv.innerHTML = "";
        });
        var questionSeq = document.getElementById("questionSeq").value;
        var questionAnswerSeq = parseInt(document.getElementById("questionAnswerSeq").value);
        var questionAnswerContent = document.getElementById("questionAnswerContent").value;

        var data = {
            questionAnswerSeq : questionAnswerSeq,
            questionAnswerContent: questionAnswerContent
        };

        var jsonData = JSON.stringify(data)

        // 서버로 POST 요청을 보냄
        fetch('/admin/question', {
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
                    text: '답변 수정을 완료했습니다.',
                    icon: 'success',
                    confirmButtonText: '확인'
                }).then(() => {
                        window.location.href = "/admin/answered-question/" + questionSeq;
                })
            }else if(data.statusCode === 401){
                // 각 에러 메시지를 동적으로 표시
                if (data.body.questionAnswerContent) {
                    displayError("#questionAnswerContent", data.body.questionAnswerContent)
                }
            }else {
                    Swal.fire('답변 수정을 실패했습니다.');
            }
        });
    });

    deleteButton.addEventListener("click", function(){

        var questionAnswerSeq = parseInt(document.getElementById("questionAnswerSeq").value);

        var data = {
                questionAnswerSeq : questionAnswerSeq
            };

        var jsonData = JSON.stringify(data);

        Swal.fire({
            title: '정말로 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: '삭제',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                // AJAX 요청으로 서버에 데이터 전송
                fetch("/admin/question", {
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: jsonData
                })
                .then(response => response.json())
                .then(data => {
                    if (data.statusCode === 200) {
                        Swal.fire({
                            text: '답변 삭제를 완료했습니다.',
                            icon: 'success',
                            confirmButtonText: '확인'
                        }).then(() => {
                             window.location.href ="/admin/question";
                        });
                    } else {
                        Swal.fire('답변 삭제를 실패했습니다.');
                    }
                })
                //AJAX END
            }
        });
    });
});