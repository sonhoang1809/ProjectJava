/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function closeItemCart() {
    document.getElementById('items-cart').style.display = 'none';
}
function displayItemCart() {
    document.getElementById('items-cart').style.display = 'block';
}
function closeSignup() {
    document.getElementById('signup-form').style.display = 'none';
}
function closeLogin() {
    document.getElementById('login-form').style.display = 'none';
}
function displayLogin() {
    document.getElementById('login-form').style.display = 'block';
}
function displaySignup() {
    document.getElementById('signup-form').style.display = 'block';
}
function fromLoginToSignup() {
    closeLogin();
    displaySignup();
}


