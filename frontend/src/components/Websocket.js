import React from 'react'
import {webSocketConnection2} from '../js/wsConnection'

export default class Websocket extends React.Component {

  componentWillMount() {
    webSocketConnection2(alert);
  }

  render () {

    return (
      <div key={Websocket.id}>
        <div id="cont" className='container'>

        </div>

      </div>
    )
  }
}