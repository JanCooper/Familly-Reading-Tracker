<template>
    <div class="center">
        <div class="bookshelf">
            <reading-list />
        </div>
        <p></p>
        <router-link class="finished-link" v-bind:to="{ name: 'new-book-form' }"
            >Add another book to your reading list</router-link
        >
    </div>
</template>

<script>
import ReadingList from "../components/ReadingList.vue";
import booksService from "@/services/BooksService.js";

export default {
    components: {
        ReadingList,
    },
    mounted() {
        booksService.getMyBooks().then((response) => {
            if (response.status === 200) {
                this.$store.commit("SET_MY_BOOKS", response.data.books);
            } else {
                alert("Conan the Librarian was unable to pull your books at this time. Please try again later.");
            }
        });
    },
};
</script>

<style>
.bookshelf {
    background-image: url("https://cdn.dribbble.com/users/417348/screenshots/2839244/attachments/583820/wood-texture-hd.jpg");
    background: linear-gradient(#e5e6e3 90%, #cabaa1 10%);
    background-size: 550px 550px;
    background-repeat: repeat;
    border: 15px solid #97886f;
    box-shadow: inset 0px 0px 20px 0px rgba(0, 0, 0, 0.6);
}
</style>
