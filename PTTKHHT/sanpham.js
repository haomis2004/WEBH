const url="http://locahost:3000/users";

fetch(url)
 .then(res => res.json())
 .then(data=> {
data.forEach(user =>{
  renderUser(user)
});
});


const tableUsers=document.querySelector('sampleTable');
const renderUser = (user) =>{
    const output=`
    <th>${user.fullName}</th>
    <th>${user.fristName}</th>
    <th>${user.img}</th>
    <th>${user.price}</th>
    <th>${user.status}</th>
    <th>${user.money}</th>
    <th>${user.category}</th>
    
    <td><button class=" btn-primary btn-sm trash" type="button" title="Xóa"
            onclick="myDeleteFunction()"><i class="fas fa-trash-alt"></i> 
        </button>
        <button class="btn-edit  btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal"
data-target="#ModalUP"><i class="fas fa-edit"></i></button>
    `;
}
