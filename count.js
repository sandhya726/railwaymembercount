let countEL=document.getElementById("count-el")
let saveEL=document.getElementById("save-el")
let count=0
function increment()
{
    count=count+1
    countEL.textContent=count

}
function save()
{
 let countstr=count+" - "
  saveEL.textContent+=countstr
  count=0
  countEL.textContent=0
}
