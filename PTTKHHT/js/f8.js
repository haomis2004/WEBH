var courses = [
  {
    id: 1,
    name: "Javascript",
    coin: 250,
  },
  {
    id: 2,
    name: "HTML, CSS",
    coin: 350,
  },
  {
    id: 3,
    name: "PHP",
    coin: 200,
  },
  {
    id: 4,
    name: "Ruby",
    coin: 0,
  },
  {
    id: 5,
    name: "Reactjs",
    coin: 500,
  },
  {
    id: 6,
    name: "React",
    coin: 500,
  },
];

// courses.forEach(function (course) {
//   console.log(course);
// });

// var isFree = courses.every(function (course) {
//   return course.coin === 0;
// });

// var isFree = courses.some(function (course) {
//   return course.coin === 0;
// });

// console.log(isFree);

// var isFree = courses.filter(function (course) {
//   return course.coin === 500;
// });

// console.log(isFree);
var userAPI =
  "http://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=ed4c0a15018939693df1a73080b85e8c";

function start() {
  getUser(renderAPI);
}
start();
// functions

function getUser(callback) {
  fetch(userAPI)
    .then((res) => res.json())
    .then(callback);
}
function renderAPI(user) {
  var listUser = document.querySelector("#list-user");

  var html = user.map((user1) => {
    return `
      <li>
        <h4>${user1.name}</h4>
      </li>    
        `;
  });
  listUser.innerHTML = html.join("");
}
