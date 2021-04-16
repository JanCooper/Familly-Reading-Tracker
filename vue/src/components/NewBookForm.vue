<template>
    <div id="new-book">
        <form class="form">
            <div>
                <h1 class="title">Add A Book</h1>
            </div>
            <div class="form-body">
                <div class="input-line">
                    <input class="title-input" type="text" placeholder="Title" v-model="book.title" />
                </div>
                <div class="input-line">
                    <input class="author-input" type="text" placeholder="Author" v-model="book.author" />
                </div>
                <div class="input-line">
                    <input class="isbn-input" type="text" placeholder="ISBN" v-model="book.isbn" />
                </div>
                <div class="center">
                    <br /><button v-on:click.prevent="saveBook()" class="button">
                        Add Book
                    </button>
                    <modal
                        v-show="isCreateBookModalVisible"
                        @close="
                            closeCreateBookModal();
                            clearFormFields();
                        "
                    >
                        <h3 slot="body">
                            Title: {{ this.book.title }}<br />
                            Author: {{ this.book.author }}<br />
                            ISBN #{{ this.book.isbn }}<br />
                        </h3>
                    </modal>
                    <modal
                        v-show="isDuplicateBookModalVisible"
                        @close="
                            closeDuplicateBookModal();
                            clearFormFields();
                        "
                    >
                        <h2 slot="header"></h2>
                        <h2 slot="body">You already have {{ this.book.title }} on your list.<br /></h2>
                    </modal>
                </div>
            </div>
        </form>
        <router-link class="finished-link" v-bind:to="{ name: 'my-books' }">
            I'm done adding books
        </router-link>
    </div>
</template>
<script>
import booksService from "../services/BooksService.js";
import modal from "@/components/Modal.vue";

export default {
    name: "new-book-form",
    components: {
        modal,
    },
    data() {
        return {
            book: {
                title: "",
                author: "",
                read: false,
                reading: false,
                isbn: "",
                imgLink: "",
            },
            isCreateBookModalVisible: false,
            isDuplicateBookModalVisible: false,
        };
    },
    computed: {
        bookURL() {
            return {
                name: "my-books",
                params: {
                    isbn: this.$route.params.isbn,
                },
            };
        },
    },
    methods: {
        saveBook() {
            booksService.create(this.book).then((response) => {
                if (response.status === 201) {
                    this.showCreateBookModal();
                    this.$store.commit("SAVE_BOOK", response.data);
                } else if (response.status === 204) {
                    this.showDuplicateBookModal();
                } else {
                    alert("Conan the Librarian was unable to add your book at this time. Please try again later.");
                }
            });
        },

        showCreateBookModal() {
            this.isCreateBookModalVisible = true;
        },

        closeCreateBookModal() {
            this.isCreateBookModalVisible = false;
        },

        showDuplicateBookModal() {
            this.isDuplicateBookModalVisible = true;
        },

        closeDuplicateBookModal() {
            this.isDuplicateBookModalVisible = false;
        },

        clearFormFields() {
            this.book.title = "";
            this.book.author = "";
            this.book.isbn = "";
            this.book.imgLink = "";
        },
    },
};
</script>
<style>
#new-book {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>
