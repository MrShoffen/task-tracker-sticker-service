package org.mrshoffen.tasktracker.task.sticker.model.dto.links;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.commons.utils.link.Link;
import org.mrshoffen.tasktracker.commons.utils.link.Links;
import org.mrshoffen.tasktracker.commons.utils.link.LinksInjector;
import org.mrshoffen.tasktracker.commons.web.dto.StickerResponseDto;
import org.mrshoffen.tasktracker.commons.web.dto.TaskResponseDto;

@RequiredArgsConstructor
public class StickerDtoLinksInjector extends LinksInjector<StickerResponseDto> {

    private final String apiPrefix;

    @Override
    protected Links generateLinks(StickerResponseDto dto) {
        return Links.builder()
                .addLink(Link.forName("deleteSticker")
                        .andHref(apiPrefix + "/workspaces/%s/desks/%s/tasks/%s/stickers/%s"
                                .formatted(dto.getWorkspaceId(), dto.getDeskId(), dto.getTaskId(), dto.getId()))
                        .andMethod("DELETE")
                        .build()
                )
                .build();
    }
}
