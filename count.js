let countEL=document.getElementById("count-el")
let count=0;
function increment()
{
    count=count+1;
    countEL.innerText=count

}
function save()
{
    console.log(count);
}
