import React from 'react'
import preloader from '../img/preloader.gif'

const Preloader = props => {
  return (
      <div className="preloader">
        <img src={preloader} alt="" className="preloader__img"/>
      </div>
  )
};

export default Preloader;
