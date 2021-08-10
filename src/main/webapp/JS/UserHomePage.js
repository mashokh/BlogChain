window.onscroll = function() {myFunction()};

var header = document.getElementById("MyNavigationBar");
var sticky = header.offsetTop;

function myFunction() {
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}
var blogModal = document.getElementById("blogModal");

var blogButton = document.getElementById("BlogButton");

var blogClose = document.getElementsByClassName("close")[0];

if(blogButton != null) {

    blogButton.onclick = function () {
        blogModal.style.display = "block";
    }

    blogClose.onclick = function () {
        blogModal.style.display = "none";
    }
}
var categoryModal = document.getElementById("categoryModal");

var categoryButton = document.getElementById("CategoryButton");

var categoryClose = document.getElementsByClassName("close")[1];

if(categoryButton != null) {
    categoryButton.onclick = function () {
        categoryModal.style.display = "block";
    }

    categoryClose.onclick = function () {
        categoryModal.style.display = "none";
    }
}