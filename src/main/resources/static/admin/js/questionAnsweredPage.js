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
