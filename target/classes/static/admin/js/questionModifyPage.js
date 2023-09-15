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
    var saveButton = document.getElementById("save-button");
    var selectedSeq = document.getElementById("selectedSeq").value;
    var files;

    var fileInput = document.getElementById("questionFiles");
    var imagePreview = document.querySelector(".img-thumbnail");
    var moreButton = document.getElementById("moreBtn");
    var imageContainer = document.getElementById("previewContainer");

    // 파일 입력 필드의 변경 이벤트를 감지합니다.
    fileInput.addEventListener("change", function() {
        // 선택된 파일 목록
        files  = fileInput.files;

        // 이전에 표시한 이미지를 삭제
        imageContainer.innerHTML = '';

        // 10개의 파일
        if (files.length > 10) {
            Swal.fire('이미지는 최대 10개까지 업로드 할 수 있습니다.');
            fileInput.value = null;
            return;
        }
    });


    //수정하려는 글의 kindSeq를 input에 숨기고 그 값이 selected되게
    var selectElement = document.getElementById("questionKindSeq");
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

        var questionSeq = document.getElementById("questionSeq").value;;
        var questionKindSeq = document.getElementById("questionKindSeq").value;
        var questionTitle = document.getElementById("questionTitle").value;
        var questionContent = document.getElementById("questionContent").value;
        var formData = new FormData();

        if(files && files.length > 0){
            for(let i = 0; i < files.length; i++){
                formData.append("questionFiles", files[i]);
            }
        }
                formData.append("questionSeq", questionSeq);
                formData.append("questionKindSeq", questionKindSeq);
                formData.append("questionTitle", questionTitle);
                formData.append("questionContent", questionContent);


       // 서버로 PUT 요청을 보냄
       fetch('/user/question', {
           method: 'PUT',
           body: formData
       })
       .then(response => response.json())
       .then(data => {000
           if (data.statusCode === 200){
               Swal.fire({
                   text: '문의하기 수정을 완료했습니다.',
                   icon: 'success',
                   confirmButtonText: '확인'
               }).then(() => {
                       window.location.href = "/user/my-question";
               })
           }else if(data.statusCode === 401){
               // 각 에러 메시지를 동적으로 표시
               if (data.body.questionKindSeq) {
                   displayError("#questionKindSeq", data.body.questionKindSeq)
               }

               if (data.body.questionTitle) {
                   displayError("#questionTitle", data.body.questionTitle)
               }

               if (data.body.questionContent) {
                   displayError("#questionontent", data.body.questionContent)
               }
           }else {
                   Swal.fire('문의하기 수정을 실패했습니다.');
           }
       });
    });

});