import React, {useEffect} from "react";
import './introductionSlider.css';
import Janta from '../Images/janta.png';
import Baterie from '../Images/baterie.jpeg';
import Monitor from '../Images/monitor.jpeg';
const IntroductionSlider =()=>{
    let time=setTimeout(()=>{},0);
    let slideindex=0;
    function changeRecipe(){
        let listItems=document.getElementsByClassName("listItem") as HTMLCollectionOf<HTMLElement>;
        for(let i=0;i<listItems.length;i++){
            listItems[i].style.display="none";
        }
        slideindex++;
        if(slideindex>=listItems.length) { slideindex=0;}
        listItems[slideindex].style.display="block";
        time=setTimeout(changeRecipe,5000);
    }
    useEffect(()=>{
        changeRecipe();
        return (()=>{
            clearTimeout(time);
        })
    },[])
    return (
        <div className="introductionSlider">
            <h1>Produse Noi!</h1>
            <ul className="productslider">
                <li className="listItem">
                    <div className="productcontent">
                        <h3>JANTA TABLA ROLLER (30504) NEAGRA 4 PREZOANE</h3>
                        <img src={Janta} alt="baterie"/>
                        <a href="https://cookieandkate.com/best-vegan-lasagna-recipe/"><i><p
                            className="p3">Order</p></i></a>
                    </div>
                </li>
                <li className="listItem">
                    <div className="productcontent">
                        <h3>BATERIE AUTO EXIDE EK111 AGM 12V 11AH, 150A</h3>
                        <img src={Baterie} alt="baterie"/>
                        <a href="https://www.crunchycreamysweet.com/homemade-nutella-chocolate-hazelnut-spread/"><i>
                            <p className="p3">Order</p></i></a>
                    </div>
                </li>
                <li className="listItem">
                    <div className="productcontent">
                        <h3>MONITOR ALPINE TME-M680EM DE 5.8INCH</h3>
                        <img src={Monitor} alt="monitor"/>
                        <a href="https://www.thereciperebel.com/no-bake-strawberry-cheesecake/"><i><p
                            className="p3">Order</p></i></a>
                    </div>
                </li>
            </ul>
        </div>
    )
}


export default IntroductionSlider;