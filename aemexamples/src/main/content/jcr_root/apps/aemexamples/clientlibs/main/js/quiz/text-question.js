$(document).ready(function() {
  var $showAnswerButton = $('.show-answer');
  var $hideAnswerButton = $('.hide-answer');
  var $answerContent = $('.answer-content');

  $showAnswerButton.on('click', function () {
    $showAnswerButton.hide();
    $hideAnswerButton.show();
    $answerContent.show();
  });

  $hideAnswerButton.on('click', function () {
    $showAnswerButton.show();
    $hideAnswerButton.hide();
    $answerContent.hide();
  });
  console.log("Document ready");
});
