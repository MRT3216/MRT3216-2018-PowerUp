let ux = {
    slider: {
        button: document.getElementById('foo')
    },
}
// My event handler
// ux.foo.button.onclick = function foo(event) {
//     console.log(event);
//     console.log(event.target);
// }

// console.log(ux.foo.button)

// ux.foo.button.addEventListener("mouseover",outputUpdate)

// ux.bar.slider.addEventListener("change",function bar(event) {
//     console.log(event);
// })

// function for speed change
function outputUpdate(speed) {
    console.log(speed)
    document.querySelector('#speed').value = speed;
}

// function for sensitivity change
function outputUpdate2(sense) {
    console.log(sense)
    document.querySelector('#sense').value = sense;
}

// function for deadspace change
function outputUpdate3(dead) {
    console.log(dead)
    document.querySelector('#dead').value = dead;
}
