import React, {useEffect, useState} from "react";
import Axios from "axios";
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'
import Button from 'react-bootstrap/Button'
import authHeader from '../services/auth-header'
import Form from 'react-bootstrap/Form'
import Multiselect from 'multiselect-react-dropdown';


const Filter = props => {

    const [tags, setTags] = useState([]);
    const [ choosenTags, setchoosenTags] = useState([])
    const [ chosenSort, setChosenSort] = useState('')
    const myRef = React.createRef()

    const fetchTags = async () => {
      const { data } = await Axios.get(
        "http://localhost:8082/tags", { headers: authHeader() }
      );
        setTags(data);
    };
  
    useEffect(() => {
        fetchTags();
    }, []);


    const onSelect = (selectedList, selectedItem) => {
        setchoosenTags(selectedList)
    }

    const onRemove = (selectedList, selectedItem) => {
        setchoosenTags(selectedList)
    }

    const handleSortChange = () => {
        setChosenSort(myRef.current.value)

    }

    const onButtonClick = async () => {
        const filters = {
            tags: choosenTags, 
            sort: myRef.current.value
        }
        props.getFilters(filters)
    }

    const onClearFilters = async () => {
        const filters = {
            tags: [],
            sort: '-'
        }
        setchoosenTags([]);
        setChosenSort('-');
        props.getFilters(filters)
    }

    return(
        <Row>
            <Col md="auto">Wbierz tagi
                <Multiselect
                isObject={false}
                hidePlaceholder={true}
                onRemove={onRemove}
                onSelect={onSelect}
                options={tags}
                selectedValues={choosenTags}
                />
            </Col>
            <Col md="auto">Wybierz sortowanie
                <Form.Control as="select"  ref={myRef} value={chosenSort} onChange={handleSortChange}>
                    <option>Najnowsze</option>
                    <option>Najstarsze</option>
                    <option>Najta??sze</option>
                    <option>Najdro??sze</option>
                    <option>Alfabetycznie A-Z</option>
                    <option>Alfabetycznie Z-A</option>
                </Form.Control>
            </Col>
            <Col md="auto"> <p></p>
                <Button className="btn-primary" onClick={onButtonClick}>Filtruj</Button>
            </Col>
            <Col md="auto"> <p></p>
                <Button className="btn-secondary" onClick={onClearFilters}>Usu?? filtry</Button>
            </Col>
        </Row>
    )
}

export default Filter