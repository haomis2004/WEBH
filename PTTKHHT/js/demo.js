const btnSearch = document.querySelector("#searchProduct");
const searchForm = document.querySelector("#searchForm");

btnSearch.addEventListener("click", showSearchForm);

function showSearchForm() {
  searchForm.classList.toggle("open");
  searchForm.style.animation = "slide-down 0.5s ease-in";
}
