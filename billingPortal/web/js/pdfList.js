var swiper = new Swiper('.swiper-container', {
    effect: 'coverflow',
    grabCursor: true,
    centeredSlides: true,
    slidesPerView: 'auto',
    coverflowEffect: {
      rotate: 30,
      stretch: 0,
      depth: 200,
      modifier: 1,
      slideShadows : true,
    },
    pagination: {
      el: '.swiper-pagination',
    },
});

function generateRandomColor()
{
    var randomColor = '#'+Math.floor(Math.random()*16777215).toString(16);
    console.log(randomColor);
    return randomColor;
    //random color will be freshly served
}

var index;
for (index = 0; index < document.getElementsByClassName('sliderText').length; index++)
{
    var node = document.getElementsByClassName('sliderText')[index];
    var anch = document.getElementsByTagName('a')[index];
    var span1 = document.getElementsByTagName('span')[index];
    var span2 = document.getElementsByTagName('lable')[index];
    var color = generateRandomColor()
    node.style.backgroundColor = color;
    anch.style.borderColor = color;
    anch.style.color = color;
    span1.style.color = color;
    span2.style.color = color;
}