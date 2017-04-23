package com.xspeedit.robot.error;

import com.xspeedit.robot.domain.Article;
import com.xspeedit.robot.domain.Bin;
import lombok.Value;

public interface Error {

    String text();

    @Value(staticConstructor = "of")
    class ArticleOversize implements Error {
        Article article;
        Bin bin;

        @Override
        public String text() {
            return String.format("Article %s trop gros pour le bin %s", article, bin.getUuid());
        }
    }

}
