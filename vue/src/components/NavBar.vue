<template>
    <div id="nav">
        <ul>
            <li v-if="$store.state.token != ''">
                <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            </li>
            <li v-if="$store.state.token != ''">
                <router-link v-bind:to="{ name: 'my-books' }">My Books</router-link>
            </li>
            <li v-if="$store.state.token != ''">
                <router-link v-bind:to="{ name: 'new-book-form' }">Add Book</router-link>
            </li>
            <li v-if="$store.state.token != ''">
                <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "nav-bar",
};
</script>

<style>
#nav {
    padding: 6px;
    font-size: 1.75rem;
}

#nav a {
    font-weight: bold;
    color: whitesmoke;
}

#nav a.router-link-exact-active {
    text-decoration: none;
    color: whitesmoke;
    text-transform: uppercase;
}

#nav ul {
    padding: 5px;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}

#nav ul li {
    list-style: none;
    width: 20%;
    background-color: #875f9a;
    border-radius: 5px;
    text-align: center;
    line-height: 50px;
    box-shadow: 4px 8px #51395c;
    cursor: pointer;
}
</style>
