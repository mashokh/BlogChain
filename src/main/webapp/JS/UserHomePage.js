window.onscroll = function() {myFunction()};

var header = document.getElementById("navigation-bar");
var sticky = header.offsetTop;

function myFunction() {
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}
var blogModal = document.getElementById("blog-modal");

var blogButton = document.getElementById("blog-button");

var blogClose = document.getElementsByClassName("close")[0];

if(blogButton != null) {

    blogButton.onclick = function () {
        blogModal.style.display = "block";
    }

    blogClose.onclick = function () {
        blogModal.style.display = "none";
    }
}
var categoryModal = document.getElementById("category-modal");

var categoryButton = document.getElementById("category-button");

var categoryClose = document.getElementsByClassName("close")[1];

if(categoryButton != null) {
    categoryButton.onclick = function () {
        categoryModal.style.display = "block";
    }

    categoryClose.onclick = function () {
        categoryModal.style.display = "none";
    }
}