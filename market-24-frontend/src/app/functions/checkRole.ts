import { User } from "../interfaces/user";

export function checkRole(): string | null{
  let jwt = localStorage.getItem('token');
  // alert(jwt);
  if(jwt != null && jwt != ''){
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    console.log(decodedJwtData.role[0]['authority']);
    return decodedJwtData.role[0]['authority'];
  }
  return null;
}

export function logout(){
  localStorage.setItem('token', '');
}

export function getUsername(): string{
  let jwt = localStorage.getItem('token');
  if(jwt != null && jwt != ''){
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    console.log(decodedJwtData.username);
    return decodedJwtData.username;
  }
  return '';
}

export function getId(): number{
  let jwt = localStorage.getItem('token');
  if(jwt != null && jwt != ''){
    let jwtData = jwt.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    console.log(decodedJwtData.id);
    return decodedJwtData.id;
  }
  return -1;
}

