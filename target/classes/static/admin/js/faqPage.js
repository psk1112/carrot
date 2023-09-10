document.addEventListener("DOMContentLoaded", function() {
    var modifyButtons = document.querySelectorAll(".modify-button");
    var deleteButtons = document.querySelectorAll(".delete-button");
    var writeButtons = document.getElementById("writeBtn");

    // 삭제 버튼 클릭 시
    deleteButtons.forEach(function(button) {
        button.addEventListener("click", function(event) {
            var row = event.target.closest("tr");
            var faqSeq = row.getAttribute("faq-seq-id");

            var data = {
                faqSeq : faqSeq
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
                    fetch("/admin/faq", {
                        method: "DELETE",
                        headers: {
                            "Content-Type": "application/json;charset=UTF-8"
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
