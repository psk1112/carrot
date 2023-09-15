document.addEventListener("DOMContentLoaded", function() {

    var links = document.querySelectorAll("a");
    var deleteButton = document.querySelectorAll(".delete-button");

    links.forEach(function(link) {
        if (link.classList.contains("answered")) {
            var modifyButton = link.closest("tr").querySelector(".modify-button");
            if (modifyButton) {
                modifyButton.style.display = "none";
            }
        }
    });

    deleteButton.forEach(function(button) {
        button.addEventListener("click", function(event) {
            var row = event.target.closest("tr");
            var questionSeq = row.getAttribute("question-seq-id");

            var data = {
                questionSeq : questionSeq
            }

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
                    fetch("/user/question", {
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
                                text: '게시물 삭제를 완료했습니다.',
                                icon: 'success',
                                confirmButtonText: '확인'
                            }).then(() => {
                                 window.location.reload();
                            });
                        } else {
                            Swal.fire('게시물 삭제를 실패했습니다.');
                        }
                    })
                    //AJAX END
                }
            });
        });
    });
});
