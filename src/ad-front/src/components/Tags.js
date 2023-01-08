import React, {useEffect, useState} from 'react'

import { InputTags } from 'react-bootstrap-tagsinput'
import 'react-bootstrap-tagsinput/dist/index.css'

const Tags = props => {
    const [state, setState] = useState([])

    useEffect(() => {
        console.log(props.value)
        if(props.value) {
            let tags = [];
            props.value.map((tag) => (
                    tags.push(tag.name)
                )
            )
            setState(tags)
        }
    }, []);


    return (
      <div style={{ margin: 10 }}>
        <div className='input-group'>
          <InputTags values={state} onTags={(value) => {
                props.addTag(value.values)
              }} />
          <button
            className='btn btn-outline-secondary'
            type='button'
            data-testid='button-clearAll'
            onClick={() => {
              setState([])
            }}
          >
            Delete all
          </button>
        </div>
        <hr />
      </div>
    )
}
export default Tags