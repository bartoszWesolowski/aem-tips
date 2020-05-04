$(document).ready(function() {

  $('.text-question').each(function(index, element) {
    var $showAnswerButton = $(element).find('.show-answer');
    var $hideAnswerButton = $(element).find('.hide-answer');
    var $answerContent = $(element).find('.answer-content');

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
  });
  console.log("Document ready");
});
