import React from 'react'
import loader from '../img/loader-feed.gif'

const Loader = () => {
  return (
      <div className="loader">
        <img src={loader} alt="loader" className="loader__img"/>
      </div>
  )
};

export default Loader;
