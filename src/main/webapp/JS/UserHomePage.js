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

function openBlogs(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tab-links");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}