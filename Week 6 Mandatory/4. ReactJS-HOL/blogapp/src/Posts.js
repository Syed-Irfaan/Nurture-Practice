import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            hasError: false
        };
    }

    loadPosts() {
        fetch("https://jsonplaceholder.typicode.com/posts")
            .then(response => response.json())
            .then(data => {
                const postList = data.map(p => new Post(p.userId, p.id, p.title, p.body));
                this.setState({ posts: postList });
            })
            .catch(error => {
                console.error("Error fetching posts:", error);
                this.setState({ hasError: true });
            });
    }

    componentDidMount() {
        this.loadPosts();
    }

    componentDidCatch(error, info) {
        alert("Something went wrong while rendering the posts.");
        console.error("Error:", error, "Info:", info);
    }

    render() {
        if (this.state.hasError) {
            return <h2>Error loading posts!</h2>;
        }

        return (
            <div>
                <h1>Posts</h1>
                {this.state.posts.map(post => (
                    <div key={post.id}>
                        <h2>{post.title}</h2>
                        <p>{post.body}</p>
                        <hr />
                    </div>
                ))}
            </div>
        );
    }
}

export default Posts;
