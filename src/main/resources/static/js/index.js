// alert();

const toggleSidebar = () => {
    if ($('.sidebar').is(":visible")) {
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};

// $(".contact-image").on("input", function () {
//
// });

// let toggleImageView = () => {
//     console.log("Input log")
//     console.log("input");
//     let image = $(this)[0];
//     if (image.files && image.files[0]) {
//         let reader = new FileReader();
//         reader.onload = function (e) {
//             document.querySelector(".view-contact-image").setAttribute("src", e.target.result);
//             console.log(e.target.result)
//         };
//
//         reader.readAsDataURL(image.files[0]);
//     }
// }