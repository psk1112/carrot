document.addEventListener("DOMContentLoaded", function() {
    var saveButton = document.getElementById("save-button");
    var questionFiles = document.getElementById('questionFiles');
    var files;

    // 에러 메시지 표시 함수
    function displayError(fieldId, errorMessage) {
        var errorDiv = document.querySelector(fieldId + " + .errordiv");
        var errorP = document.createElement("p");
        errorP.textContent = errorMessage;
        errorP.style.color = "red";
        errorDiv.appendChild(errorP);
    }

    // 파일선택 최대 10개까지
    questionFiles.addEventListener('change', function(event) {
        files = questionFiles.files;

        if (files.length > 10) {
            event.preventDefault(); // 파일 선택 취소
            Swal.fire('이미지는 최대 10개까지 업로드 할 수 있습니다.');

            // 선택한 파일 원복
            questionFiles.value = null;
        }
    });

    // 저장 버튼 클릭 시
    saveButton.addEventListener("click", function() {

        // 이전 에러 메시지 삭제
        document.querySelectorAll(".errordiv").forEach(function(errordiv) {
            errordiv.innerHTML = "";
        });

        var questionKindSeq = document.getElementById("questionKindSeq").value;
        var questionTitle = document.getElementById("questionTitle").value;
        var questionContent = document.getElementById("questionContent").value;
        var formData = new FormData();

        if(files && files.length > 0){
            for(let i = 0; i < files.length; i++){
                formData.append("questionFiles", files[i]);
            }
        }
                formData.append("questionKindSeq", questionKindSeq);
                formData.append("questionTitle", questionTitle);
                formData.append("questionContent", questionContent);


        // 서버로 POST 요청을 보냄
        fetch('/user/question', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.statusCode === 200) {
                Swal.fire({
                    text: '문의하기 등록을 완료했습니다.',
                    icon: 'success',
                    confirmButtonText: '확인'
                }).then(() => {
                window.location.href = "/user/my-question";
                })
            } else if (data.statusCode === 401) {
                // 각 에러 메시지를 동적으로 표시

                if (data.body.questionTitle) {
                    displayError("#questionTitle", data.body.questionTitle)
                }

                if (data.body.questionContent) {
                    displayError("#questionContent", data.body.questionContent)
                }
            } else {
                Swal.fire('문의하기 등록을 실패했습니다.');
            }
        });
    });

});