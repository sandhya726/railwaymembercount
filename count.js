let countEL=document.getElementById("count-el")
let saveEL=document.getElementById("save-el")
let count=0
function increment()
{
    count=count+1
    countEL.innerText=count

}
function save()
{
  saveEL.innerText+=" "+count+" - "
  count=0;
  countEL.innerText=count;
  
}
