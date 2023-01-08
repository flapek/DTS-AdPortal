import React, {useEffect, useState} from "react";
import Comment from '../components/Comment'
import Container from 'react-bootstrap/Container'
import AddComment from "./AddComment";
import AuthService from "../services/auth.service";
import axios from "axios";
import authHeader from "../services/auth-header";

const CommentList = props => {
  const [comments, setComments] = useState([]);
  const [username, setUsername] = useState('');

  const getComments = async () => {
    if (props.offer.id!=null) axios.get("http://localhost:8082/offer/" + props.offer.id + "/comments")
          .then((response) => {
            setComments(response.data)
          });
  };

  const deleteComment = async (commentId) => {
    if (props.offer.id!=null) await axios.delete("http://localhost:8082/deleteComment/" + props.offer.id + "/"
        + commentId, { headers: authHeader() })
        .then((response) => {
          setComments(response.data)
        });
  };

  useEffect(() => {
    const user = AuthService.getCurrentUser()

    if (user) setUsername(user.username);

    getComments().catch((errors) => { console.log(errors)});
  }, []); // usunalem comment z deps, bo useEffect sie zapętlał


  return (
      <div>
        {comments.length!==0 ?
            <div>
              <h4>Komentarze: </h4>
              <Container style={{textAlign: "left"}}>
                {comments.map((comment) => (
                    <Comment key={comment.id}
                             id={comment.id}
                             user={comment.user}
                             content={comment.content}
                             currentUser={username}
                             deleteComment={deleteComment}
                    />
                ))}
              </Container>
            </div> : <p>Brak komentarzy</p>
        }
        <Container>
          <div hidden={username===''}>
            <AddComment offer={props.offer} username={username} updateComments={setComments}/>
          </div>
        </Container>
      </div>
  );
}

export default CommentList;