import React from 'react'

export let showHideBlocks = (toShow, toHide) => {
  let shown = document.getElementById(toShow);
  let hidden = document.getElementById(toHide);
  shown.classList.remove("d-none");
  hidden.classList.add("d-none");
};