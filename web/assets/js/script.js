/* global URL */

function userHasProfilePicture() {
    let imageProfilePicture = document.getElementById('img-profile-picture');
    let inputProfilePicture = document.getElementById('profilepictureuser');
    
    if (inputHasFile(inputProfilePicture) === false) {
        console.log('primeiro if');
        console.log(imageProfilePicture.src);
        if (isAvatarImage(imageProfilePicture)) {
            console.log('segundo if');
            changeToAvatarImage(imageProfilePicture);
        } else {
            console.log('else');
        }
    } else {
        loadImageFile(imageProfilePicture, inputProfilePicture);
    }
}

function inputHasFile(fileInputElement) {
    if (fileInputElement.files.length === 0) {
        return false;
    } else {
        return true;
    }
}

function isAvatarImage(imageElement) {
    if (imageElement.src === '/ProjetoAcademiaOnline/assets/images/avatar.webp') {
        return true;
    } else if(imageElement.src === 'http://localhost:8080/ProjetoAcademiaOnline/ShowUserProfilePicture?idperson=') {
        return true;
    } else {
        return false;
    }
}

function changeToAvatarImage(imageElement, imagePath = '/ProjetoAcademiaOnline/assets/images/avatar.webp') {
    imageElement.src = imagePath;
}

function loadImageFile(imageElement, fileInputElement) {
    imageElement.src = URL.createObjectURL(fileInputElement.files[0]);
    imageElement.onload = function () {
        URL.revokeObjectURL(imageElement.src);
    };
}


let inputProfilePicture = document.getElementById('profilepictureuser');

inputProfilePicture.addEventListener('input', () => {
    userHasProfilePicture();
});

window.onload = () => {
    userHasProfilePicture();
};