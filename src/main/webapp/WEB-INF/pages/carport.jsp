<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <h2 class="mt-4 mb-4 text-center">Sammensæt din helt egen carport herunder</h2>
    <br>
        <form novalidate>
            <div class="form-group">
                <label for="laengde">Carportens længde</label>
                <select class="form-control" id="laengde" required>
                    <option value="500">500cm</option>

                    <option value="780">780cm</option>
                </select>
            </div>

            <div class="form-group">
                <label for="bredde">Carportens bredde</label>
                <select class="form-control" id="bredde" required>
                    <option value="300">300cm</option>

                    <option value="600">600cm</option>
                </select>
            </div>

            <div class="form-group">
                <!-- TODO: Tekst som siger at alle tage har en hældning over 15 grader -->
                <label for="tag">Carportens tagtype</label>
                <select class="form-control" id="tag" required>
                    <option value="flat">Fladt</option>
                    <option value="peak">Med rejsning</option>
                </select>
            </div>

            <div class="custom-control custom-switch">
                <input type="checkbox" class="custom-control-input" id="shed" onchange="document.getElementById('shedtype').disabled = !this.checked;">
                <label class="custom-control-label" for="shed">Jeg ønsker et shed</label>
            </div>
            <div class="form-group">
                <label for="shedtype">Skur type</label>
                <select class="form-control" id="shedtype" disabled>
                    <option value="50">Hel bredde</option>
                    <option value="100">Halv bredde</option>
                </select>
            </div>

            <div class="form-group">
                <label for="name">Din email</label>
                <input type="name" class="form-control" id="name" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <label for="email">Din email</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" required>
            </div>
            <div class="form-group">
                <label for="phone">Dit telefonnummer</label>
                <input type="phone" class="form-control" id="phone" required>
            </div>


            <button type="submit" class="btn btn-primary">Send forespørgelse</button>
        </form>