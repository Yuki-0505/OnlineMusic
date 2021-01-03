// var serverURL = "http://localhost:8080";
// var serverURL = "http://localhost:8080/WangYiYun";

const curWwwPath = window.document.location.href;
const pathName = window.document.location.pathname;
const index = curWwwPath.indexOf(pathName);

const localhostPath = curWwwPath.substring(0, index);

const projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var serverURL = localhostPath + projectName;
