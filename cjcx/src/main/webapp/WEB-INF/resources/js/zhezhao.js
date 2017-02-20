/**
 * Created by Administrator on 2017/1/26.
 */
window.onload=function()
{
    var zhezhao=document.getElementById("zhezhao");
    var neirong=document.getElementById("neirong");
    var bt=document.getElementById("open");
    var btclose=document.getElementById("close");

    bt.onclick=function()
    {
        zhezhao.style.display="block";
        neirong.style.display="block";
    }
    btclose.onclick=function()
    {
        zhezhao.style.display="none";
        neirong.style.display="none";
    }
}